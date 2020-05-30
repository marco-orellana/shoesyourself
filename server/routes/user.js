const express = require('express')
const router = express.Router()
const UserController = require('../controllers/user')

router.get('/', UserController.getAll)
router.post('/', UserController.SubmitUser)
router.get('/:userId', UserController.GetUserById)
router.get('/:userId/cart', UserController.GetCartById)
router.get('/:userId/order', UserController.GetOrderById)

module.exports = router
