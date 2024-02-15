<template>
	<v-card>
		<v-card-title>
			<h2>Les users</h2>
		</v-card-title>
		<v-card-text>
			<v-data-table :headers="headers" :items="users">
			
			</v-data-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListUsers",
	data() {
		return {
			users: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "Email", sortable: true, value: "email"},
				{ text: "First Name", sortable: true, value: "firstname"},
				{ text: "Last Name", sortable: true, value: "lastname"},
				{ text: "Login", sortable: true, value: "login"},
				{ text: "Password", sortable: true, value: "password"},
				{ text: "Permission Level", sortable: true, value: "permission"}
			]
		}
	},
	computed: {
		users1() {
			return this.$store.getters['users/getAllUsers'];
		}
	},
	watch: {
		users1: function(users1) {
			this.users = users1;
		}
	},
	mounted() {
		this.$store.dispatch('users/getUsers')
	}
}
</script>