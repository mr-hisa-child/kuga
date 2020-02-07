import api from '@/utils/api'
export const state = () => ({
    teamList: [],
    active: null
})

export const mutations = {
    save(state, payload) {
        state.teamList = payload
        if(payload.length === 0){
            return
        }

        const activeTeamId = localStorage.getItem('kuga-team')
        if(!activeTeamId || payload.length === 1){
            state.active = payload[0]
        }else{
            const activeTeam = payload.filter(team => team.id === activeTeamId)
            state.active = activeTeam[0]
        }
        
        localStorage.setItem('kuga-team',state.active.id)
    },
}

export const actions = {
    async getTeam({ commit }, payload) {
        const res = await api.getTeamList()
		if (res.status == 200) {
			// 正常
            commit('save', res.data)
		} else {
			window.$nuxt.$emit('showMessage', 'システムエラー', 'error', 5000)
		}
        
    }
}

export const getters = {
    activeTeam: (state) => {
        return state.active
    },
}