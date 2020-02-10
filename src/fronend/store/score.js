import api from '@/utils/api'
export const state = () => ({
    scoreList: null
})

export const mutations = {
    save(state, payload) {
        state.scoreList = payload
    },
    update(state, payload) {
        state.scoreList.filter(score => score.id === payload.id).forEach(score => {
            score.goal = payload.goal
            score.win = payload.win
            score.assist = payload.assist
        })
    }
}

export const actions = {
    async findByActivityId({ commit }, activityId) {
        const res = await api.getScoreList(activityId)
        if (res.status === 200) {
            commit('save', res.data)
        } else {

        }
    },
    async findByYear({ commit }, year) {

        const res = await api.getScoreListByYear(year)
        if (res.status === 200) {
            commit('save', res.data)
        } else {

        }
    },
    async increment({ commit }, payload) {

        const res = await api.increment(payload.scoreId, payload.category)
        if (res.status === 200) {
            commit('update', res.data)
        } else {

        }
    },
    async decrement({ commit }, payload) {
        const res = await api.decrement(payload.scoreId, payload.category)
        if (res.status === 200) {
            commit('update', res.data)
        } else {

        }
    },
    save({ commit }, payload) {
        commit('save', payload)
    }
}

export const getters = {
    scoreList: (state) => {
        return state.scoreList
    },
}