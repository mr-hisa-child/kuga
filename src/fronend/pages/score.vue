<template>
  <v-layout>score</v-layout>
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