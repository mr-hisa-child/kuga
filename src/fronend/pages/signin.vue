<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="12" md="4">
          <v-card class="elevation-12">
            <v-toolbar color="primary" dark flat>
              <v-toolbar-title>サインイン</v-toolbar-title>
            </v-toolbar>
            <v-card-text>
              <v-form>
                <v-text-field
                  label="メールアドレス"
                  name="login"
                  prepend-icon="mdi-account"
                  type="text"
                  v-model="email"
                />

                <v-text-field
                  id="password"
                  label="パスワード"
                  name="password"
                  prepend-icon="mdi-lock"
                  type="password"
                  v-model="password"
                />
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-spacer />
              <v-btn color="primary" @click="signin" rounded>サインイン</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>
<script>
import api from '@/utils/api'
export default {
	layout: 'none',
	data() {
		return {
			email: null,
			password: null
		}
	},
	methods: {
		async signin() {
			const data = {
				email: this.email,
				password: this.password
			}
			const res = api.signin(data)

			if (res.status == 200) {
				// 正常
				localStorage.setItem('token', res.headers.authorization)
				this.$router.push('/member')
			} else if (res.status == 401) {
				// 認証エラー
				this.$nuxt.$emit(
					'showMessage',
					'認証情報が違います',
					'error',
					5000
				)
			} else {
				this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
			}
		}
	}
}
</script>