const express = require('express')
const router = express.Router()
const BrandController = require('../controllers/brand')

router.get('/', BrandController.getAll)
router.post('/', BrandController.SubmitBrand)
router.get('/:brandId', BrandController.GetBrandById)

module.exports = router
