// production 导入时使用views下的文件
module.exports = file => () => import("@/views/" + file + '.vue')