<template>
  <div>
    <v-simple-table class="bottom-nav-margin">
      <template v-slot:default>
        <tbody>
          <tr v-for="item in items" :key="item.id" @click="selectRow(item)">
            <td class="title">{{ item.name }}</td>
          </tr>
        </tbody>
        <v-btn
          fixed
          dark
          fab
          bottom
          right
          color="red"
          class="bottom-nav-margin"
          @click="showDialog()"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </template>
    </v-simple-table>
    <v-dialog v-model="dialog" width="500" persistent>
      <v-card>
        <v-form ref="form" v-model="valid" :lazy-validation="lazy">
          <v-card-title class="headline">メンバー</v-card-title>
          <v-card-text>
            <v-text-field v-model="form.name" :counter="20" :rules="nameRules" label="名前" required></v-text-field>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-btn outlined color="error" @click="deleteMember()" v-show="form.id">削除</v-btn>
            <v-spacer></v-spacer>
            <v-btn outlined @click="dialog = false">キャンセル</v-btn>
            <v-btn outlined color="success" :disabled="!valid" @click="saveMember()">保存</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import api from '@/utils/api'
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      items: null,
      targetItem: {},
      dialog: false,
      form: {
        name: null
      },
      valid: true,
      lazy: true,
      nameRules: [
        v => !!v || '名前を入力してください',
        v => (v && v.length <= 20) || '名前は20文字以内で入力してください'
      ]
    }
  },
  created() {
    this.load()
  },
  methods: {
    async load() {
      const res = await api.getMemberList()

      if (res.status == 200) {
        // 正常
        this.items = res.data
      } else {
        this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
      }
    },
    selectRow(item) {
      this.form = {
        id: item.id,
        name: item.name
      }
      this.dialog = true
    },
    async deleteMember() {
      const res = await api.deleteMember(this.form.id)
      if (res.status === 204) {
        this.load()
        this.dialog = false
      } else {
        this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
      }
    },
    async saveMember() {
      const res = await api.saveMember(this.form)
      if (res.status === 200 || res.status === 201) {
        this.load()
        this.dialog = false
      } else {
        this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
      }
    },
    showDialog() {
      this.form = {
        id: null,
        name: null
      }
      this.valid = true
      this.dialog = true
    }
  }
}
</script>