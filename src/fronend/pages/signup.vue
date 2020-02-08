<template>
  <v-card>
    <v-toolbar color="primary" dark flat>
      <v-toolbar-title>サインアップ</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-form>
        <v-text-field
          label="名前"
          name="name"
          prepend-icon="mdi-account"
          type="text"
          v-model="form.name"
        />
        <v-text-field
          label="メールアドレス"
          name="login"
          prepend-icon="mdi-account"
          type="text"
          v-model="form.email"
        />

        <v-text-field
          id="password"
          label="パスワード"
          name="password"
          prepend-icon="mdi-lock"
          type="password"
          v-model="form.password"
        />
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-spacer />
      <v-btn color="primary" @click="signup" outlined rounded>登録</v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
import api from '@/utils/api'
export default {
  layout: 'none',
  data() {
    return {
      form: {
        name: null,
        email: null,
        password: null
      }
    }
  },
  methods: {
    async signup() {
      const res = await api.signup(this.form)
      if (res.status === 200) {
        this.$router.push('/signin')
      } else {
      }
    }
  }
}
</script>