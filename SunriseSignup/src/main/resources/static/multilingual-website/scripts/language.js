document.addEventListener('DOMContentLoaded', () => {
    const supportedLanguages = ["en", "fr", "es"];
    const urlPath = window.location.pathname;
    const languageCode = urlPath.split('/')[1];  // Assumes URL format like /en/page.html

    if (supportedLanguages.includes(languageCode)) {
        // Language is supported, do nothing as the correct page is already served
    } else {
        // Redirect to the default language (e.g., /en/index.html)
        window.location.href = '/en/index.html';
    }
});
