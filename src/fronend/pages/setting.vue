<template>
  <div>
    <v-list subheader>
      <v-subheader>チーム</v-subheader>

      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>新規作成</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>招待</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>削除</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>

    <v-divider></v-divider>

    <v-list subheader>
      <v-subheader>アカウント</v-subheader>

      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>パスワード変更</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>ログアウト</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>
<script>
import Header from '@/components/Header'
export default {
	//layout: 'none',
	components: {
		Header
	}
}
</script>