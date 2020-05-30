const express = require('express')
const router = express.Router()
const ProductsController = require('../controllers/product')

router.get('/', ProductsController.getAll)
router.post('/', ProductsController.SubmitProduct)
router.get('/:productId', ProductsController.GetProductById)
router.delete('/:productId', ProductsController.DeleteProduct)
router.put('/:productId', ProductsController.UpdateProduct)

module.exports = router
