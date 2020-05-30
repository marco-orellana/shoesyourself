const Product = require('../models/Product')

exports.getAll = async (req, res) => {
    try {
        const products = await Product.find()
        res.json(products)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.SubmitProduct = async (req, res) => {
    const product = new Product({
        brand_id: req.body.brand_id,
        title: req.body.title,
        description: req.body.description,
        img_url: req.body.img_url,
        price: req.body.price
    })

    try {
        const savedProduct = await product.save()
        res.json(savedProduct)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.GetProductById = async (req, res) => {
    try {
        const product = await Product.findById(req.params.productId)
        res.json(product)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.DeleteProduct = async (req, res) => {
    try {
        const removeProduct = await Product.deleteOne({ _id: req.params.productId })
        res.json(removeProduct)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.UpdateProduct = async (req, res) => {
    try {
        const updatedProduct = await Product.updateMany({ _id: req.params.productId },
            {
                $set: {
                    brand_id: req.body.brand_id,
                    title: req.body.title,
                    description: req.body.description,
                    img_url: req.body.img_url,
                    price: req.body.price
                }
            })
        res.json(updatedProduct)
    } catch (err) {
        res.json({ message: err })
    }
}
