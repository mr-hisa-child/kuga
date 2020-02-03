<template>
  <v-app dark>
    <v-app-bar fixed app>
      <v-toolbar-title v-text="title" />
      <v-spacer />
      {{teamName}}
    </v-app-bar>
    <v-content>
      <v-container>
        <nuxt />
      </v-container>
    </v-content>

    <v-bottom-navigation :value="activeBtn" grow fixed>
      <v-btn to="/member" @click="title = 'メンバー'">
        <span>メンバー</span>
        <v-icon>mdi-account</v-icon>
      </v-btn>

      <v-btn to="/activity" @click="title = '活動履歴'">
        <span>活動履歴</span>
        <v-icon>mdi-calendar-check</v-icon>
      </v-btn>

      <v-btn to="/record" @click="title = '個人成績'">
        <span>個人成績</span>
        <v-icon>mdi-grid</v-icon>
      </v-btn>

      <v-btn to="/setting" @click="title = '設定'">
        <span>設定</span>
        <v-icon>mdi-settings</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script>
import api from '@/utils/api'
export default {
	data() {
		return {
			title: 'れこった',
			activeBtn: 1,
			teamName: null
		}
	},
	created() {
		const teamId = localStorage.getItem('kuga-team')
		const res = api.getTeam(teamId)
		if (res.status == 200) {
			// 正常
			this.teamName = res.data.teamName
		} else {
			this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
		}
	}
}
</script>
