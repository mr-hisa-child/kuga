<template>
  <v-simple-table class="bottom-nav-margin">
    <template v-slot:default>
      <tbody>
        <tr v-for="item in scoreList" :key="item.id">
          <td class="title">{{ item.name }}</td>
          <td class="text-center" style="width:88px">
            <v-btn
              class="mx-2"
              outlined
              fab
              dark
              small
              color="error"
              @click="increment(item.id,category)"
            >
              <v-icon dark>mdi-minus</v-icon>
            </v-btn>
          </td>
          <td class="text-center display-1" style="width:30px">{{ item[category] }}</td>
          <td class="text-center" style="width:88px">
            <v-btn
              class="mx-2"
              outlined
              fab
              dark
              small
              color="success"
              @click="decrement(item.id,category)"
            >
              <v-icon dark>mdi-plus</v-icon>
            </v-btn>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import api from '@/utils/api'
import { mapGetters, mapActions } from 'vuex'
export default {
	props: {
		category: {
			type: String,
			default: ''
		}
	},
	computed: {
		...mapGetters({
			scoreList: 'score/scoreList'
		})
	},
	methods: {
		...mapActions({
			increment: 'score/increment',
			decrement: 'score/decrement'
		})
	}
}
</script>