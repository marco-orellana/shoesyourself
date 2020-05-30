const Brand = require('../models/Brand')

exports.getAll = async (req, res) => {
    try {
        const brands = await Brand.find()
        res.json(brands)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.SubmitBrand = async (req, res) => {
    const brand = new Brand({
        title: req.body.title
    })

    try {
        const savedBrand = await brand.save()
        res.json(savedBrand)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.GetBrandById = async (req, res) => {
    try {
        const brand = await Brand.findById(req.params.brandId)
        res.json(brand)
    } catch (err) {
        res.json({ message: err })
    }
}
