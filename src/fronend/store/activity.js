export const state = () => ({
    id: null
})

export const mutations = {
    setId(state, payload) {
    state.id = payload
  },
}

export const actions = {
setId({ commit }, payload) {
    commit('setId', payload)
  },
}

export const getters = {
getId: (state) => {
    return state.id
  },
}