import os
import requests
from bs4 import BeautifulSoup

# Azure Translator API credentials
API_KEY = '298842085ea448f0bff5c2591fbde401'  # Your actual API Key
ENDPOINT = 'https://api.cognitive.microsofttranslator.com'
API_URL = f"{ENDPOINT}/translate?api-version=3.0"
REGION = 'southeastasia'  # Your Azure region

# Languages to translate to (language codes)
languages = ["en", "fr", "es"]

# Directory paths
input_dir = r"C:\multilingual-website\original_html"  # Path to your original HTML files
output_dir = r"C:\multilingual-website\translated_html"  # Path to the output directory

def translate_text(text, target_language):
    headers = {
        'Ocp-Apim-Subscription-Key': API_KEY,
        'Ocp-Apim-Subscription-Region': REGION,
        'Content-type': 'application/json'
    }
    body = [{'text': text}]
    params = {'to': target_language}
    
    try:
        response = requests.post(API_URL, headers=headers, params=params, json=body)
        response.raise_for_status()
        translation = response.json()
        return translation[0]['translations'][0]['text']
    except requests.exceptions.RequestException as e:
        print(f"Error translating text: {e}")
        return text  # Fallback to the original text if translation fails

def translate_html_file(file_path, target_language):
    with open(file_path, 'r', encoding='utf-8') as file:
        soup = BeautifulSoup(file, 'html.parser')

    # Translate text in <p>, <h1>, <h2>, etc.
    for tag in soup.find_all(['p', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'title', 'a', 'span']):
        if tag.string and not tag.string.isspace():
            try:
                translated_text = translate_text(tag.string, target_language)
                tag.string.replace_with(translated_text)
            except Exception as e:
                print(f"Error translating tag: {e}")

    # Create the output directory for the target language
    output_language_dir = os.path.join(output_dir, target_language)
    os.makedirs(output_language_dir, exist_ok=True)
    
    # Write the translated HTML to a new file
    output_file_path = os.path.join(output_language_dir, os.path.basename(file_path))
    with open(output_file_path, 'w', encoding='utf-8') as file:
        file.write(str(soup))

def main():
    if not os.path.exists(input_dir):
        print(f"Input directory '{input_dir}' does not exist.")
        return

    for language in languages:
        print(f"Translating to '{language}'...")
        for filename in os.listdir(input_dir):
            if filename.endswith('.html'):
                file_path = os.path.join(input_dir, filename)
                translate_html_file(file_path, language)
                print(f"Translated {filename} to {language}")

if __name__ == "__main__":
    main()
