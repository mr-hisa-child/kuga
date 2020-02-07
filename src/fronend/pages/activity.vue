<template>
  <div>
    <YearFilter @change-year="reload" />
    <v-row>
      <v-col>
        <v-simple-table class="bottom-nav-margin">
          <template v-slot:default>
            <tbody>
              <tr v-for="item in items" :key="item.title" @click="selectRow(item)">
                <td style="width:100px">{{ item.date | formatDate}}</td>
                <td>{{ item.title }}</td>
                <td style="width:80px" class="text-right">{{ item.count }}人</td>
              </tr>
            </tbody>
            <v-btn fixed dark fab bottom right color="pink" class="bottom-nav-margin" @click="showDialog()">
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
    <v-dialog v-model="dialog" width="500" persistent>
      <v-card>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-card-title class="headline">活動</v-card-title>

          <v-card-text>
            <v-text-field
              v-model="form.title"
              :counter="20"
              :rules="nameRules"
              label="タイトル"
              required
            ></v-text-field>
            <v-menu
              v-model="picker"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field v-model="form.date" label="活動日" readonly v-on="on"></v-text-field>
              </template>
              <v-date-picker v-model="form.date" @input="picker = false"></v-date-picker>
            </v-menu>
            <v-btn outlined block color="info" @click="selectScore()" v-show="form.id">スコア管理</v-btn>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-btn outlined color="error" @click="deleteActivity()" v-show="form.id">削除</v-btn>
            <v-spacer></v-spacer>
            <v-btn outlined @click="dialog = false">キャンセル</v-btn>
            <v-btn outlined color="success" :disabled="!valid" @click="saveActivity()">保存</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import YearFilter from '@/components/YearFilter'
import { mapActions } from 'vuex'
import api from '@/utils/api'
export default {
	components: {
		YearFilter
	},
	data() {
		return {
			items: null,
			targetItem: {},
			dialog: false,
			year: null,
			form: {
        id: null,
				date: null,
				title: null
			},
			valid: true,
			nameRules: [
				v => !!v || '名前を入力してください',
				v =>
					(v && v.length <= 20) ||
					'名前は20文字以内で入力してください'
			],
			picker: false
		}
	},
    filters:{
        formatDate: (date) => {
            const splitDate = date.split('-')
            return `${splitDate[1]}/${splitDate[2]}`
        }
    },
	methods: {
		...mapActions({
			setActivityId: 'activity/setId'
		}),
		selectRow(item) {
			this.targetItem = item
			this.form = {
        id: item.id,
				date: item.date,
				title: item.title
			}
			this.dialog = true
		},
		selectScore() {
			this.setActivityId(this.targetItem.id)
			this.$router.push('/score')
		},
		async deleteActivity() {
			const res = await api.deleteActivity(this.targetItem.id)
			if (res.status == 200) {
				this.reload(this.year)
				this.dialog = false
			} else {
				this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
			}
		},
		async saveActivity() {
      const res = await api.saveActivity(this.form)
      if (res.status === 200 || res.status === 201) {
        this.reload(this.year)
        this.dialog = false
      } else {
        this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
      }
    },
		async reload(year) {
			this.year = year
			const res = await api.getActivityList(year)

			if (res.status == 200) {
				// 正常
				this.items = res.data
			} else {
				this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
			}
    },
    showDialog(){
        this.form = {
            id: null,
            date: null,
            title: null
        }
        this.valid= true
        // this.$refs.form.resetValidation()
        this.dialog = true
    }
	}
}
</script>