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
        <v-overlay :value="overlay">
          <v-progress-circular indeterminate size="64"></v-progress-circular>
        </v-overlay>
        <v-snackbar v-model="snackbar" bottom :color="color" :timeout="timeout" right>
          {{ message }}
          <v-btn dark text @click="snackbar = false">Close</v-btn>
        </v-snackbar>
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
import { mapActions, mapGetters } from 'vuex'
export default {
  data() {
    return {
      title: 'れこった',
      activeBtn: 1,
      teamName: null,
      overlay: false,
      snackbar: false,
      color: null,
      timeout: 3000,
      message: null
    }
  },
  computed: {
    ...mapGetters({
      activeTeam: 'team/activeTeam'
    })
  },

  async created() {
    await this.getTeam()
    this.teamName = this.activeTeam.name

    this.$nuxt.$on('loading', this.loading)
    this.$nuxt.$on('showMessage', this.showMessage)
  },
  methods: {
    ...mapActions({
      getTeam: 'team/getTeam'
    }),
    loading(isLoading) {
      this.overlay = isLoading
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
