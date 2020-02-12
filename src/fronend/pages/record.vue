<template>
  <div>
    <YearFilter @change-year="reload" />
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
import YearFilter from '@/components/YearFilter'
import { mapGetters, mapActions } from 'vuex'
export default {
  components: {
    ScoreList,
    YearFilter
  },
  data() {
    return {
      tab: null
    }
  },
  methods: {
    ...mapActions({
      getScoreListByYear: 'score/findByYear'
    }),
    async reload(year) {
      this.$nuxt.$emit('loading', true)
      await this.getScoreListByYear(year)
      this.$nuxt.$emit('loading', false)
    }
  }
}
</script>