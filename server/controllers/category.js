/*
const Category = require('../models/Category')

exports.getAll = async (req, res) => {
    try {
        const categories = await Category.find()
        res.json(categories)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.SubmitCategory = async (req, res) => {
    const category = new Category({
        title: req.body.title
    })

    try {
        const savedCategory = await category.save()
        res.json(savedCategory)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.GetCategoryById = async (req, res) => {
    try {
        const category = await Category.findById(req.params.categoryId)
        res.json(category)
    } catch (err) {
        res.json({ message: err })
    }
}
*/
