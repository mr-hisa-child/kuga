<template>
  <v-app dark>
    <v-app-bar fixed app>
      <v-toolbar-title v-text="title" />
      <v-spacer />
    </v-app-bar>
    <v-content>
      <v-container>
        <nuxt />
      </v-container>
    </v-content>

    <v-bottom-navigation :value="activeBtn" grow>
      <v-btn to="/member" @click="title = 'メンバー'">
        <span>メンバー</span>
        <v-icon>mdi-account</v-icon>
      </v-btn>

      <v-btn to="/activity" @click="title = '活動履歴'">
        <span>活動履歴</span>
        <v-icon>mdi-calendar-check</v-icon>
      </v-btn>

      <v-btn to="/score" @click="title = 'スコア'">
        <span>スコア</span>
        <v-icon>mdi-grid</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script>
export default {
	data() {
		return {
			title: 'Vuetify.js',
			activeBtn: 1
		}
	}
}
</script>
