import telebot
import json
import whisper
import os
import requests
import whisper
import os
from pydub import AudioSegment


# Altere para o token do seu bot
API_TOKEN = '7294511682:AAHRE7qD_1QY6WawO_a0nbPzPlTiL45p0O0'

bot = telebot.TeleBot(API_TOKEN)

def get_function_details(llm, message, tools):
    try:
      result = llm.create_chat_completion(
          messages=[{"role": "user", "content": message}],
          tools=tools,
          tool_choice="auto",
      )
      function_call = result["choices"][0]["message"]["tool_calls"][0]["function"]
      function_name = function_call["name"]
      arguments = json.loads(function_call["arguments"])
      return function_name, arguments
    except (IndexError, KeyError, json.JSONDecodeError):
      return None, None

def execute_function(function_name, arguments, functions_map):
    if function_name in functions_map:
      try:
          function = functions_map[function_name]
          return function(**arguments)
      except TypeError as e:
        return f"Desculpe, houve um problema ao executar a função: {e}"
    return "Desculpe, eu não entendi o que você quis dizer."

@bot.message_handler(func=lambda message: True)
def reply_to_message(message):
    function_name, arguments = get_function_details(llm, message.text, tools)
    response = execute_function(function_name, arguments, functions_map)
    # bot.reply_to(message, response)

def convert_audio_to_wav(input_path, output_path):
    """
    Converte um arquivo de áudio para o formato WAV.
    """
    sound = AudioSegment.from_ogg(input_path)
    sound.export(output_path, format="wav")

@bot.message_handler(content_types=['audio', 'voice'])
def handle_audio(message):
    if message.audio:
        file_id = message.audio.file_id
        file_extension = '.ogg'
    elif message.voice:
        file_id = message.voice.file_id
        file_extension = '.ogg'

    file_info = bot.get_file(file_id)
    file_url = f"https://api.telegram.org/file/bot{API_TOKEN}/{file_info.file_path}"

    # Baixar o arquivo de áudio
    response = requests.get(file_url)
    audio_path = f"received_audio{file_extension}"

    with open(audio_path, 'wb') as f:
        f.write(response.content)

    # Converter o arquivo para WAV
    wav_path = "received_audio.wav"
    convert_audio_to_wav(audio_path, wav_path)

    # Processar o áudio com Whisper
    model = whisper.load_model("base")
    audio = whisper.load_audio(wav_path)
    audio = whisper.pad_or_trim(audio)
    mel = whisper.log_mel_spectrogram(audio).to(model.device)
    _, probs = model.detect_language(mel)
    print(f"Detected language: {max(probs, key=probs.get)}")
    options = whisper.DecodingOptions()
    result = whisper.decode(model, mel, options)

    function_name, arguments = get_function_details(llm, result.text, tools)

    response = execute_function(function_name, arguments, functions_map)
    convertToAudio(response, "A female speaker, delivering an animated and expressive speech at a moderate pace and pitch. The recording is of very high quality, with the speaker's voice sounding clear and close, conveying a sense of warmth and proximity.")
    with open('parler_tts_out.wav', 'rb') as audio_file:
      bot.send_audio(message.chat.id, audio_file)
    #bot.reply_to(message, response)

    # Limpar arquivos temporários
    os.remove(audio_path)
    os.remove(wav_path)

bot.polling()
