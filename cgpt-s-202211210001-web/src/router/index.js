/**
 * 路由配置
 */
import { createRouter, createWebHashHistory } from 'vue-router' //createWebHashHistory

const routes = [{
        path: '/login',
        name: 'Login',
        component: () =>
            import ('../components/Login.vue')
    },
    {
        //重定向
        path: '/',
        redirect: '/login'
    },
    {
        path: '/main',
        name: 'Main',
        component: () =>
            import ('../components/Main.vue'),
        redirect: '/account',
        children: [{
                path: '/account',
                name: 'Account',
                component: () =>
                    import ('../components/admin/account.vue')
            },
            {
                path: '/carcase',
                name: 'Carcase',
                component: () =>
                    import ('../components/admin/casecar.vue')
            },
            {
                path: '/udi',
                name: 'Udi',
                component: () =>
                    import ('../components/admin/udi.vue')
            },
            {
                path: '/setno',
                name: 'Setno',
                component: () =>
                    import ('../components/admin/setno.vue')
            },
            {
                path: '/package',
                name: 'Package',
                component: () =>
                    import ('../components/admin/package.vue')
            },
            {
                path: '/room',
                name: 'Room',
                component: () =>
                    import ('../components/admin/room.vue')
            },
            {
                path: '/disinfection',
                name: 'Disinfection',
                component: () =>
                    import ('../components/admin/disinfection.vue')
            },
            {
                path: '/position',
                name: 'Position',
                component: () =>
                    import ('../components/admin/position.vue')
            },
            {
                path: '/surgical',
                name: 'Surgical',
                component: () =>
                    import ('../components/admin/surgical.vue')
            },

            {
                path: '/depno',
                name: 'Depno',
                component: () =>
                    import ('../components/admin/depno.vue')
            },
            {
                path: '/box',
                name: 'Box',
                component: () =>
                    import ('../components/admin/box.vue')
            },
            {
                path: '/tag',
                name: 'Tag',
                component: () =>
                    import ('../components/user/tag.vue')
            },
            {
                path: '/reprint',
                name: 'Reprint',
                component: () =>
                    import ('../components/user/reprint.vue')
            },
            {
                path: '/indicator',
                name: 'Indicator',
                component: () =>
                    import ('../components/user/indicator.vue')
            },
            {
                path: '/warehousing',
                name: 'Warehousing',
                component: () =>
                    import ('../components/user/warehousing.vue')
            },
            {
                path: '/apply',
                name: 'Apply',
                component: () =>
                    import ('../components/user/apply.vue')
            },
            {
                path: '/allocate',
                name: 'Allocate',
                component: () =>
                    import ('../components/user/allocate.vue')
            },
            {
                path: '/receive',
                name: 'Receive',
                component: () =>
                    import ('../components/user/receive.vue')
            },
            {
                path: '/relocate',
                name: 'Relocate',
                component: () =>
                    import ('../components/user/relocate.vue')
            },
            {
                path: '/errolocate',
                name: 'Errolocate',
                component: () =>
                    import ('../components/user/errolocate.vue')
            },
            {
                path: '/report',
                name: 'Report',
                component: () =>
                    import ('../components/user/report.vue')
            },
            {
                path: '/kpi',
                name: 'Kpi',
                component: () =>
                    import ('../components/user/kpi.vue')
            },
            {
                path: '/pot',
                name: 'Pot',
                component: () =>
                    import ('../components/user/pot.vue')
            },
            {
                path: '/use',
                name: 'Use',
                component: () =>
                    import ('../components/user/use.vue')
            },
            {
                path: '/exchange',
                name: 'Exchange',
                component: () =>
                    import ('../components/user/exchange.vue')
            },
            {
                path: '/setno_seq',
                name: 'Seq',
                component: () =>
                    import ('../components/admin/seq.vue')
            },
            {
                path: '/report/02',
                name: 'Report02',
                component: () =>
                    import ('../components/user/report02.vue')
            },
        ]
    },


]

const router = createRouter({
    history: createWebHashHistory(process.env.BASE_URL),
    routes
})

export default router