<template>
  <div>
    <v-row>
      <v-col>
        <v-btn dart outlined rounded block color="primary" @click="dialog=true">参加者を追加する</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-tabs v-model="tab" class="elevation-2" dark grow>
          <v-tabs-slider></v-tabs-slider>
          <v-tab href="#tab-goal">ゴール</v-tab>
          <v-tab href="#tab-assist">アシスト</v-tab>
          <v-tab href="#tab-win">勝ち数</v-tab>

          <v-tab-item value="tab-goal">
            <ScoreList category="goal" editable></ScoreList>
          </v-tab-item>
          <v-tab-item value="tab-assist">
            <ScoreList category="assist" editable></ScoreList>
          </v-tab-item>
          <v-tab-item value="tab-win">
            <ScoreList category="win" editable></ScoreList>
          </v-tab-item>
        </v-tabs>
      </v-col>
    </v-row>
    <v-dialog v-model="dialog" fullscreen hide-overlay>
      <v-card>
        <v-app-bar dark color="primary" fixed>
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title>参加メンバーを選択</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn dark text @click="saveAttend()">保存</v-btn>
        </v-app-bar>
        <v-content>
          <v-simple-table>
            <template v-slot:default>
              <tbody>
                <tr v-for="item in memberList" :key="item.id">
                  <td class="title">{{ item.name }}</td>
                  <td>
                    <v-checkbox v-model="attend" :value="item.id"></v-checkbox>
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-content>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import ScoreList from '@/components/ScoreList'
import { mapGetters, mapActions } from 'vuex'
import api from '@/utils/api'
export default {
  components: {
    ScoreList
  },
  data() {
    return {
      tab: null,
      tabs: 3,
      dialog: false,
      memberList: null,
      attend: []
    }
  },
  computed: {
    ...mapGetters({
      activityId: 'activity/getId',
      scoreList: 'score/scoreList'
    })
  },
  created() {
    this.getScoreListByActivityId(this.activityId)
    this.loadMemberList()
  },
  methods: {
    ...mapActions({
      getScoreListByActivityId: 'score/findByActivityId'
    }),
    loadMemberList() {
      const res = api.getMemberList()

      if (res.status == 200) {
        // 正常
        this.memberList = res.data
      } else {
        this.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
      }
    },
    saveAttend() {}
  }
}
</script>