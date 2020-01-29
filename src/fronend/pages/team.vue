<template>
  <div>
    <v-app-bar fixed app>
      <v-toolbar-title v-text="title" />
      <v-spacer />
    </v-app-bar>
    <v-content>
      <v-container>
        <v-form ref="form" v-model="valid" :lazy-validation="lazy">
          <v-text-field v-model="name" :counter="20" :rules="nameRules" label="チーム名" required></v-text-field>

          <v-select
            v-model="category"
            :items="categoryList"
            :rules="[v => !!v || 'カテゴリを選択してください']"
            label="カテゴリ"
            required
          ></v-select>

          <v-btn rounded :disabled="!valid" color="primary" block class="mt-10" @click="save">登録</v-btn>
        </v-form>
      </v-container>
    </v-content>
  </div>
</template>

<script>
export default {
	layout: 'none',
	data() {
		return {
			title: 'チーム登録',
			valid: true,
			name: null,
			category: null,
			categoryList: [
				'BEGINNER',
				'SUPER BEGINNER',
				'ENJOY',
				'MIX',
				'OPEN'
			],
			nameRules: [
				v => !!v || 'チーム名を入力してください',
				v =>
					(v && v.length <= 20) ||
					'チーム名は20文字以内で入力してください'
			]
		}
	},
	methods: {
		async save() {
			const data = {
				name: this.name,
				category: this.category
			}
			const res = this.$axios.post('/team', data).catch(err => {
				return err.response
			})

			if (res.status == 200) {
				// 正常
				this.$router.push('/member')
			} else if (res.status == 400) {
				// 認証エラー
				this.$nuxt.$emit(
					'showMessage',
					'入力内容に誤りがあります',
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