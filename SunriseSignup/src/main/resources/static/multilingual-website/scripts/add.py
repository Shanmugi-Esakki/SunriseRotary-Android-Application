import os
from bs4 import BeautifulSoup

# Ensure the path is correct with double slashes or raw string
base_dir = r"C:\multilingual-website\translated_html"

def add_script_tag(directory):
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.html'):
                file_path = os.path.join(root, file)
                print(f"Processing {file_path}")  # Debugging line

                with open(file_path, 'r', encoding='utf-8') as f:
                    soup = BeautifulSoup(f, 'html.parser')

                script_tag = soup.new_tag('script', src='/scripts/language.js')
                
                # Check and add script tag to <body>, <head>, or end of document
                if soup.body:
                    if not soup.find('script', {'src': '/scripts/language.js'}):
                        soup.body.append(script_tag)
                        print(f"Added script to <body> in {file_path}")  # Debugging line
                elif soup.head:
                    if not soup.find('script', {'src': '/scripts/language.js'}):
                        soup.head.append(script_tag)
                        print(f"Added script to <head> in {file_path}")  # Debugging line
                else:
                    if not soup.find('script', {'src': '/scripts/language.js'}):
                        soup.append(script_tag)
                        print(f"Added script to end of document in {file_path}")  # Debugging line

                # Write the modified HTML back to the file
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.write(str(soup))

# Run the function on the base directory
add_script_tag(base_dir)
