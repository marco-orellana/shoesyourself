const Role = require('../models/Role')

exports.getAll = async (req, res) => {
    try {
        const roles = await Role.find()
        res.json(roles)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.SubmitRole = async (req, res) => {
    const role = new Role({
        title: req.body.title
    })

    try {
        const savedRole = await role.save()
        res.json(savedRole)
    } catch (err) {
        res.json({ message: err })
    }
}

exports.GetRoleById = async (req, res) => {
    try {
        const role = await Role.findById(req.params.roleId)
        res.json(role)
    } catch (err) {
        res.json({ message: err })
    }
}
