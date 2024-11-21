import sqlite3
import requests

key_omdb = 'ec2e5269'

# Funções
def greet_user():
    return "Hello, how can I help you today ?"

def api_find_movie(name):
    name = name.split()
    movie = [word.capitalize() for word in name]
    movie = '+'.join(movie)
    url = 'https://www.omdbapi.com/?apikey=' + key_omdb +'&&t='+ movie;

    data = requests.get(url).json();
    if (data['Response'] == 'True'):
      return data;
    return False

def search_movie(name):
    data = api_find_movie(name)
    if (api_find_movie(name) != False):
      return 'Founded movies:' + '\n' '• Name: ' + data['Title'] + '\n' '• Year:' + data['Year'] + '\n' '• Rating: ' + data['imdbRating'] + '\n' + '• Runtime: ' + data['Runtime'] + '\n' + '• Genre: ' + data['Genre'];
    return 'No movie found'

# Estrutura para armazenar o carrinho
favList = {}

def add_to_fav_list(movie_title):
    data = api_find_movie(movie_title)
    if data['Response'] != False:
      if data['Title'] not in favList:
        favList[data['Title']] = data['Genre']
      result = 'Movies of your list:\n'
      for movie in favList:
        result += f'• {movie}\n'
      return result.strip();
    return 'No movie found'

def remove_from_fav_list(movie_title):
    data = api_find_movie(movie_title)
    if data['Response'] != False:
        if data['Title'] in favList:
            favList.pop(data['Title'])
        else:
            return 'No movie found'
        result = 'Movies of your list:\n'
        for movie in favList:
          result += f'• {movie}\n'
        return result.strip();

def view_fav_list():
    if favList:
        result = 'Movies of your list:\n'
        for movie in favList:
          result += f'• {movie}\n'
        return result.strip();
    else:
        return "Your favorite list is empty"

def view_fav_list_by_genre(genre):
    if favList:
        genreList = []
        genre = genre.lower()
        result = 'Movies of your list with genre ' + genre + ':\n'
        for movie in favList:
            movie_genre_list = favList[movie].lower().split(', ')
            if genre in movie_genre_list:
                genreList.append(movie)
                result += f'• {movie}\n'
        if genreList:
          return result.strip()
        else:
          return "No movie found with genre " + genre + "."
    else:
        return "Your favorite list is empty"


# Mapeamento das funções
functions_map = {
    "greet_user": greet_user,
    "search_movie": search_movie,
    "add_to_fav_list": add_to_fav_list,
    "remove_from_fav_list": remove_from_fav_list,
    "view_fav_list": view_fav_list,
    "view_fav_list_by_genre": view_fav_list_by_genre,
}

# Ferramentas do chatbot
tools = [
    {
        "type": "function",
        "function": {
            "name": "search_movie",
            "description": "Search for a specific movie",
            "parameters": {
                "type": "object",
                "properties": {"name": {"type": "string", "description": "The name of the book"}},
                "required": ["name"],
            },
        },
    },
    {
        "type": "function",
        "function": {
            "name": "greet_user",
            "description": "Greet the user and ask how to help",
            "parameters": {
                "type": "object",
                "properties": {},
                "required": [],
            },
        },
    },
    {
        "type": "function",
        "function": {
            "name": "add_to_fav_list",
            "description": "Add a movie to the favorite list",
            "parameters": {
                "type": "object",
                "properties": {
                    "movie_title": {"type": "string", "description": "The title of the movie to add"},
                },
                "required": ["movie_title"],
            },
        },
    },
    {
        "type": "function",
        "function": {
            "name": "remove_from_fav_list",
            "description": "Remove a movie from the favorite list",
            "parameters": {
                "type": "object",
                "properties": {
                    "movie_title": {"type": "string", "description": "The title of the book to remove"},
                },
                "required": ["movie_title"],
            },
        },
    },
    {
        "type": "function",
        "function": {
            "name": "view_fav_list",
            "description": "View the favorite list",
            "parameters": {
                "type": "object",
                "properties": {},
                "required": [],
            },
        },
    },
    {
        "type": "function",
        "function": {
            "name": "view_fav_list_by_genre",
            "description": "Retrieve and display movies from the favorite list that match the specified genre.",
            "parameters": {
                "type": "object",
                "properties": {
                    "genre": {"type": "string", "description": "The genre of the movies to display"},
                },
                "required": [],
            },
        },
    },
]
