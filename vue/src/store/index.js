import Vue from 'vue'
import Vuex from 'vuex'
import UserData from '@/store/UserData'
import DocsData from '@/store/DocsData'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        UserData,
        DocsData
    }
})
