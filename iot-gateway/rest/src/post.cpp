#include <iostream>
#include <string>
#include <curl/curl.h>

// Fonction callback pour traiter la réponse HTTP
size_t WriteCallback(void* contents, size_t size, size_t nmemb, std::string* output) {
    size_t total_size = size * nmemb;
    output->append((char*)contents, total_size);
    return total_size;
}

int main() {
    // Adresse du serveur Quarkus
    const char* server_url = "http://192.168.0.40:8887/Station";

    // Données JSON à envoyer
    const char* json_data = R"({
    "name": "Test",
    "longitude": "12.2",
    "latitude": "3.5"
    })";
    
    // Initialisation de libcurl
    CURL* curl;
    CURLcode res;

    curl_global_init(CURL_GLOBAL_DEFAULT);
    curl = curl_easy_init();

    if(curl) {
        // Configuration de l'URL et des données à envoyer
        curl_easy_setopt(curl, CURLOPT_URL, server_url);
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, json_data);

        // Ajout des en-têtes nécessaires pour une requête POST avec des données JSON
        struct curl_slist* headers = NULL;
        headers = curl_slist_append(headers, "Content-Type: application/json");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

        // Configuration de la fonction de rappel pour traiter la réponse HTTP
        std::string response_data;
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response_data);

        // Effectue la requête POST
        res = curl_easy_perform(curl);

        // Vérification des erreurs
        if(res != CURLE_OK)
            fprintf(stderr, "Erreur lors de l'envoi de la requête : %s\n", curl_easy_strerror(res));
        else
            std::cout << "Réponse du serveur : " << response_data << std::endl;

        // Nettoyage
        curl_slist_free_all(headers);
        curl_easy_cleanup(curl);
    }

    // Libération des ressources libcurl
    curl_global_cleanup();

    return 0;
}
