const express = require('express')
const router = express.Router()
const CartListController = require('../controllers/cartlist')

router.get('/:cartId', CartListController.GetCartListsById)
router.post('/', CartListController.SubmitProductInCart)
router.delete('/:cartListId', CartListController.DeleteProductInCart)
router.put('/:cartListId', CartListController.UpdateProductInCart)

module.exports = router
