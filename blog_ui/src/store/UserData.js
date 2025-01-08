export default {
    namespaced: true,
    state: {
        UserInfo: []
    },
    getters: {},
    mutations: {
        SET_USER_INFO(state, value) {
            state.UserInfo = value
        }
    },
    actions: {
        demo(context) {
            context.commit('SET_USER_INFO')
        }
    },
}
