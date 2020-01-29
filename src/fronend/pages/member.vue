<template>
  <div>
    <!-- <v-layout> -->
    <v-data-table
      :items="items"
      :headers="headers"
      hide-default-header
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
					text: '名前',
					align: 'left',
					value: 'name'
				}
			],
			items: [
				{
					name: '小野'
				},
				{
					name: '佐藤'
				}
			]
		}
	}
}
</script>