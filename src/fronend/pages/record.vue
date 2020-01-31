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
        <v-tabs v-model="tab" class="elevation-2" dark grow>
          <v-tabs-slider></v-tabs-slider>
          <v-tab href="#tab-attend">参加数</v-tab>
          <v-tab href="#tab-goal">ゴール</v-tab>
          <v-tab href="#tab-assist">アシスト</v-tab>
          <v-tab href="#tab-win">勝ち数</v-tab>

          <v-tab-item value="tab-attend">
            <ScoreList category="attend"></ScoreList>
          </v-tab-item>
          <v-tab-item value="tab-goal">
            <ScoreList category="goal"></ScoreList>
          </v-tab-item>
          <v-tab-item value="tab-assist">
            <ScoreList category="assist"></ScoreList>
          </v-tab-item>
          <v-tab-item value="tab-win">
            <ScoreList category="win"></ScoreList>
          </v-tab-item>
        </v-tabs>
      </v-col>
    </v-row>
  </div>
</template>
<script>
import ScoreList from '@/components/ScoreList'
import { mapGetters, mapActions } from 'vuex'
export default {
  components: {
    ScoreList
  },
  data() {
    return {
      tab: null,
      tabs: 3,
      year: 2020
    }
  },
  created() {
    this.getScoreListByYear(this.year)
  },
  methods: {
    ...mapActions({
      getScoreListByYear: 'score/findByYear'
    })
  }
}
</script>