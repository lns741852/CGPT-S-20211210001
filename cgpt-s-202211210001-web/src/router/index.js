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
        path: '/setno_search',
        name: 'SetnoSearch',
        component: () =>
            import ('../components/common/setno_search.vue')
    },
    {
        path: '/main',
        name: 'Main',
        component: () =>
            import ('../components/Main.vue'),
        redirect: '/tag',
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
                path: '/udi/:id',
                name: 'Udi',
                component: () =>
                    import ('../components/admin/udi.vue')
            },
            {
                path: '/udi_type',
                name: 'UdiType',
                component: () =>
                    import ('../components/admin/udiType.vue')
            },
            {
                path: '/setno',
                name: 'Setno',
                component: () =>
                    import ('../components/admin/setno.vue')
            },
            {
                path: '/setno2/:id',
                name: 'Setno2',
                component: () =>
                    import ('../components/admin/setno2.vue')
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
                path: '/relocate2/:id',
                name: 'Relocate2',
                component: () =>
                    import ('../components/user/relocate2.vue')
            },
            {
                path: '/report',
                name: 'Report',
                component: () =>
                    import ('../components/user/report.vue')
            },
            {
                path: '/fix',
                name: 'Fix',
                component: () =>
                    import ('../components/user/fix.vue')
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
                path: '/report2',
                name: 'Report2',
                component: () =>
                    import ('../components/user/report2.vue')
            },
            {
                path: '/report3',
                name: 'Report3',
                component: () =>
                    import ('../components/user/report3.vue')
            },
            {
                path: '/report4',
                name: 'Report4',
                component: () =>
                    import ('../components/user/report4.vue')
            },
            {
                path: '/report5',
                name: 'Report5',
                component: () =>
                    import ('../components/user/report5.vue')
            },
            {
                path: '/report6',
                name: 'Report6',
                component: () =>
                    import ('../components/user/report6.vue')
            },
            {
                path: '/report7',
                name: 'Report7',
                component: () =>
                    import ('../components/user/report7.vue')
            },
            {
                path: '/report8',
                name: 'Report8',
                component: () =>
                    import ('../components/user/report8.vue')
            },
            {
                path: '/report9',
                name: 'Report9',
                component: () =>
                    import ('../components/user/report9.vue')
            },
            {
                path: '/report10',
                name: 'Report10',
                component: () =>
                    import ('../components/user/report10.vue')
            },
            {
                path: '/report11',
                name: 'Report11',
                component: () =>
                    import ('../components/user/report11.vue')
            },
            {
                path: '/apply/print/:id',
                name: 'ApplyPrint',
                component: () =>
                    import ('../components/user/applyPrint.vue')
            },
            {
                path: '/allocate2/:id',
                name: 'Allocate2',
                component: () =>
                    import ('../components/user/allocate2.vue')
            },
            {
                path: '/allocate3/:id',
                name: 'Allocate3',
                component: () =>
                    import ('../components/user/allocate3.vue')
            },
            {
                path: '/search',
                name: 'Search',
                component: () =>
                    import ('../components/user/search.vue')
            },
            {
                path: '/search2',
                name: 'Search2',
                component: () =>
                    import ('../components/user/search2.vue')
            },
            {
                path: '/search3',
                name: 'Search3',
                component: () =>
                    import ('../components/user/search3.vue')
            },
            {
                path: '/search4',
                name: 'Search4',
                component: () =>
                    import ('../components/user/search4.vue')
            },
            {
                path: '/search5',
                name: 'Search5',
                component: () =>
                    import ('../components/user/search5.vue')
            },
            {
                path: '/search6',
                name: 'Search6',
                component: () =>
                    import ('../components/user/search6.vue')
            },
            {
                path: '/search6_2/:casecarno',
                name: 'Search6_2',
                component: () =>
                    import ('../components/user/search6_2.vue')
            },
            {
                path: '/search7',
                name: 'Search7',
                component: () =>
                    import ('../components/user/search7.vue')
            },
            {
                path: '/search2_2/:barcode',
                name: 'Search2_2',
                component: () =>
                    import ('../components/user/search2_2.vue')
            },
            {
                path: '/search2_3/:id',
                name: 'Search2_3',
                component: () =>
                    import ('../components/user/search2_3.vue')
            },
            {
                path: '/setno_udi',
                name: 'Setno_udi',
                component: () =>
                    import ('../components/user/setno_udi.vue')
            },
            {
                path: '/setno_udi2/:id',
                name: 'Setno_udi2',
                component: () =>
                    import ('../components/user/setno_udi2.vue')
            },

        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(process.env.BASE_URL),
    routes
})

export default router