<template>
        <v-container>
          <!-- Informations de contact -->
          <v-row justify="center" class="mb-4">
            <v-col cols="12" sm="8" md="6">
              <v-card>
                <v-card-title class="headline blue lighten-1">Informations de contact</v-card-title>
                <v-card-text>
                  <v-list>

                    <v-list-item>
                      <v-list-item-icon>
                         <v-icon>mdi-map-marker</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title>Adresse: 50 Rue Barthélémy de Laffemas, 26000 Valence</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>

                    <v-list-item>
                      <v-list-item-icon>
                        <v-icon>mdi-phone</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title>Numéro de téléphone: +07 56 99 02 13</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
      
                    <v-list-item>
                      <v-list-item-icon>
                        <v-icon>mdi-email</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title>Adresse e-mail: contact@slt.com</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
      
                    <v-list-item>
                      <v-list-item-icon>
                        <v-icon>mdi-gitlab</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title>GitLab: https://gricad-gitlab.univ-grenoble-alpes.fr/cs550-applications-iot/2023-2024/cs550-groupe01/snowlifttracker</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
      
                    <v-list-item>
                      <v-list-item-icon>
                        <v-icon>mdi-instagram</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title>Instagram: @snowlift-tracker-fr</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
      
                    <v-list-item>
                      <v-list-item-icon>
                        <v-icon>mdi-facebook</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title>Facebook: @snowlift-tracker-fr</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>

                    <v-list-item>
                     <v-list-item-icon>
                      <v-icon>mdi-twitter</v-icon>
                     </v-list-item-icon>
                     <v-list-item-content>
                      <v-list-item-title>Twitter: @snowlift-tracker-fr</v-list-item-title>
                     </v-list-item-content>
                    </v-list-item>
                  </v-list>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
      
          <!-- Formulaire de contact -->
          <v-row justify="center">
            <v-col cols="12" sm="8" md="6">
              <v-card>
                <v-card-title class="headline blue lighten-1">Contactez-nous</v-card-title>
                <v-card-text>
                  <v-form @submit.prevent="submitForm">
                    <v-text-field v-model="name" label="Nom" required></v-text-field>
                    <v-text-field v-model="email" label="Adresse Email" required></v-text-field>
                    <v-textarea v-model="message" label="Message" required></v-textarea>
                    <v-btn type="submit" color="primary">Envoyer</v-btn>
                  </v-form>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
</template>
      
<script>
      export default {
        data() {
          return {
            name: '',
            email: '',
            message: ''
          };
        },
        methods: {
          submitForm() {
            // Ajoutez ici la logique pour envoyer le formulaire
            console.log('Nom:', this.name);
            console.log('Email:', this.email);
            console.log('Message:', this.message);
      
            // Réinitialisez le formulaire après l'envoi
            this.name = '';
            this.email = '';
            this.message = '';
          }
        }
      };
</script>
      
<style>
/* Ajoutez ici vos styles personnalisés si nécessaire */
</style>