import time
import openai
import pdfkit
import PyPDF2
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

# OpenAI API key
openai.api_key = "YOUR_OPENAI_API_KEY"

# LinkedIn login credentials
linkedin_username = "YOUR_LINKEDIN_USERNAME"
linkedin_password = "YOUR_LINKEDIN_PASSWORD"

# Start a Selenium WebDriver session
driver = webdriver.Chrome(executable_path="path_to_chromedriver")
driver.get("https://www.linkedin.com/login")

# Login to LinkedIn
username = driver.find_element_by_id("username")
password = driver.find_element_by_id("password")
username.send_keys(linkedin_username)
password.send_keys(linkedin_password)
password.send_keys(Keys.RETURN)

# Allow some time for the login process to complete
time.sleep(5)

# Perform search on LinkedIn
search_keyword = "Software Engineer"  # Change to your desired keyword
driver.get(f"https://www.linkedin.com/search/results/people/?keywords={search_keyword}")

# Extract profile links
profile_links = []
profiles = driver.find_elements_by_css_selector("a.search-result__result-link")
for profile in profiles:
    link = profile.get_attribute("href")
    if link and "/in/" in link:  # Filter valid profile URLs
        profile_links.append(link)

# Iterate through profiles, download as PDF, extract data, generate personalized note, and send connection requests
for link in profile_links:
    driver.get(link)
    time.sleep(3)  # Allow time for profile to load

    # Save profile as PDF
    pdf_path = f"{link.split('/')[-1]}.pdf"
    pdfkit.from_url(link, pdf_path)
    
    # Extract text from the PDF
    with open(pdf_path, "rb") as file:
        pdf_reader = PyPDF2.PdfReader(file)
        profile_text = ""
        for page in range(len(pdf_reader.pages)):
            profile_text += pdf_reader.pages[page].extract_text()
    
    # Generate personalized note using OpenAI
    prompt = f"Based on this profile information:\n\n{profile_text}\n\nWrite a personalized connection request note for a profile relevant to my background in software engineering."
    response = openai.Completion.create(
        model="gpt-4",
        prompt=prompt,
        max_tokens=100
    )
    personalized_note = response.choices[0].text.strip()

    # Send a connection request
    connect_button = driver.find_element_by_xpath("//button[text()='Connect']")
    connect_button.click()
    
    time.sleep(2)
    
    # Add a personalized note
    note_field = driver.find_element_by_id("custom-message")
    note_field.send_keys(personalized_note)
    
    send_button = driver.find_element_by_xpath("//button[text()='Send']")
    send_button.click()
    
    time.sleep(2)

driver.quit()
