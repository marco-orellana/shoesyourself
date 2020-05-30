const express = require('express')
const router = express.Router()
const RoleController = require('../controllers/role')

router.get('/', RoleController.getAll)
router.post('/', RoleController.SubmitRole)
router.get('/:roleId', RoleController.GetRoleById)

module.exports = router
