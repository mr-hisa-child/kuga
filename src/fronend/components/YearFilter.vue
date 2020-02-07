<template>
  <v-row>
    <v-col>
      <v-btn dark fab outlined color="primary" x-small @click="changeYear(-1)">
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
    </v-col>
    <v-col class="title text-center">{{year}}年</v-col>
    <v-col class="text-right">
      <v-btn dark fab outlined color="primary" x-small @click="changeYear(1)">
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
    </v-col>
  </v-row>
</template>

<script>
export default {
	data() {
		return {
			year: null
		}
	},
	created() {
		this.year = this.$moment().year()
		this.$emit('change-year', this.year)
	},
	methods: {
		changeYear(v) {
			this.year = this.year + v
			this.$emit('change-year', this.year)
		}
	}
}
</script>
