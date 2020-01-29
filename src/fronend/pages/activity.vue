<template>
  <div>
    <!-- <v-layout> -->
    <v-data-table
      :items="items"
      :headers="headers"
      hide-default-footer
      class="elevation-1"
      :mobile-breakpoint="mobileBreakpoint"
    ></v-data-table>
  </div>
</template>
<script>
export default {
	data() {
		return {
			mobileBreakpoint: 0,
			headers: [
				{
					text: '活動日',
					align: 'left',
					value: 'date',
					width: '20%'
				},
				{
					text: 'タイトル',
					align: 'left',
					value: 'title',
					width: '60%'
				},
				{
					text: '参加人数',
					align: 'left',
					value: 'count',
					width: '20%'
				}
			],
			items: [
				{
					date: '2019/12/12',
					title: '練習',
					count: 5
				},
				{
					date: '2019/12/12',
					title: '練習',
					count: 5
				},
				{
					date: '2019/12/12',
					title: '練習',
					count: 5
				}
			]
		}
	}
}
</script>