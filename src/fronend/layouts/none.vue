<template>
  <v-app dark>
    <nuxt />
    <v-snackbar v-model="snackbar" bottom :color="color" :timeout="timeout" right>
      {{ message }}
      <v-btn dark text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
  </v-app>
</template>

<script>
export default {
	data() {
		return {
			snackbar: false,
			color: null,
			timeout: 3000,
			message: null
		}
	},
	created() {
		this.setListener()
	},
	methods: {
		setListener() {
			this.$nuxt.$on('showMessage', this.showMessage)
		},
		showMessage(message, color, timeout) {
			console.log('hoge')
			this.message = message
			this.color = color
			this.timeout = timeout
			this.snackbar = true
		}
	}
}
</script>
