<template>
  <div>
    <v-row>
      <v-col>
        <v-btn dark fab outlined color="primary" x-small>
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
      </v-col>
      <v-col class="title text-center">{{year}}年</v-col>
      <v-col class="text-right">
        <v-btn dark fab outlined color="primary" x-small>
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-simple-table class="bottom-nav-margin">
          <template v-slot:default>
            <tbody>
              <tr v-for="item in items" :key="item.title" @click="selectRow(item)">
                <td style="width:100px">{{ item.date }}</td>
                <td>{{ item.title }}</td>
                <td style="width:80px" class="text-right">{{ item.count }}人</td>
              </tr>
            </tbody>
            <v-btn fixed dark fab bottom right color="pink" class="bottom-nav-margin">
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
    <v-dialog v-model="dialog" width="500" persistent>
      <v-card>
        <v-form ref="form" v-model="valid" :lazy-validation="lazy">
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
            <v-btn outlined block color="info" @click="selectScore()">スコア管理</v-btn>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-btn outlined color="error" @click="dialog = false">削除</v-btn>
            <v-spacer></v-spacer>
            <v-btn outlined @click="dialog = false">キャンセル</v-btn>
            <v-btn outlined color="success" @click="dialog = false">保存</v-btn>
          </v-card-actions>
        </v-form>
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
      dialog: false,
      year: 2020,
      form: {
        date: null,
        title: null
      },
      valid: true,
      lazy: true,
      nameRules: [
        v => !!v || '名前を入力してください',
        v => (v && v.length <= 20) || '名前は20文字以内で入力してください'
      ],
      picker: false
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