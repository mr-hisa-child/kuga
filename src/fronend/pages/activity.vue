<template>
  <div>
    <v-simple-table class="bottom-nav-margin">
      <template v-slot:default>
        <tbody>
          <tr v-for="item in items" :key="item.title" @click="selectRow(item)">
            <td>{{ item.date }}</td>
            <td>{{ item.title }}</td>
            <td>{{ item.count }}人</td>
          </tr>
        </tbody>
        <v-btn fixed dark fab bottom right color="pink" class="bottom-nav-margin">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </template>
    </v-simple-table>
    <v-dialog v-model="dialog" width="500">
      <v-card>
        <!-- <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          Privacy Policy
        </v-card-title>-->

        <v-card-text>{{targetItem.date}} {{targetItem.title}}</v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn outlined color="success" @click="dialog = false">保存</v-btn>
          <v-btn outlined color="error" @click="dialog = false">削除</v-btn>
          <v-btn outlined color="info" @click="selectScore()">スコア管理</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
import api from '@/utils/api'
export default {
	data() {
		return {
			items: null,
			targetItem: {},
			dialog: false
		}
	},
	created() {
		const res = api.getActivityList()

		if (res.status == 200) {
			// 正常
			this.items = res.data
		} else {
			this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
		}
	},
	methods: {
		...mapActions({
			setActivityId: 'activity/setId'
		}),
		selectRow(item) {
			this.targetItem = item
			this.dialog = true
		},
		selectScore() {
			this.setActivityId(this.targetItem.id)
			this.$router.push('/score')
		}
	}
}
</script>