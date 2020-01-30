<template>
  <v-layout>
    <v-tabs v-model="tab" class="elevation-2" dark grow>
      <v-tabs-slider></v-tabs-slider>
      <v-tab href="#tab-attend">参加数</v-tab>
      <v-tab href="#tab-goal">ゴール</v-tab>
      <v-tab href="#tab-assist">アシスト</v-tab>
      <v-tab href="#tab-win">勝ち数</v-tab>

      <v-tab-item value="tab-attend">
        <v-data-table
          :items="items"
          :headers="headers"
          hide-default-header
          hide-default-footer
          class="elevation-1"
          :mobile-breakpoint="mobileBreakpoint"
        ></v-data-table>
      </v-tab-item>
      <v-tab-item value="tab-goal">
        <v-data-table
          :items="items"
          :headers="headers"
          hide-default-header
          hide-default-footer
          class="elevation-1"
          :mobile-breakpoint="mobileBreakpoint"
        ></v-data-table>
      </v-tab-item>
      <v-tab-item value="tab-assist">
        <v-data-table
          :items="items"
          :headers="headers"
          hide-default-header
          hide-default-footer
          class="elevation-1"
          :mobile-breakpoint="mobileBreakpoint"
        ></v-data-table>
      </v-tab-item>
      <v-tab-item value="tab-win">
        <v-data-table
          :items="items"
          :headers="headers"
          hide-default-header
          hide-default-footer
          class="elevation-1"
          :mobile-breakpoint="mobileBreakpoint"
        ></v-data-table>
      </v-tab-item>
    </v-tabs>
  </v-layout>
</template>
<script>
export default {
  data() {
    return {
      mobileBreakpoint: 0,
      headers: [
        {
          text: '名前',
          align: 'left',
          value: 'name'
        },
        {
          text: 'スコア',
          align: 'left',
          value: 'score'
        }
      ],
      items: [
        {
          name: '小野',
          score: 2
        },
        {
          name: '佐藤',
          score: 2
        }
      ],
      tab: null,
      tabs: 3
    }
  }
}
</script>