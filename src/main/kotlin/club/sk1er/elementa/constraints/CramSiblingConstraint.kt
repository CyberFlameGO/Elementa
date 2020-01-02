package club.sk1er.elementa.constraints

import club.sk1er.elementa.UIComponent

/**
 * Similar to a [SiblingConstraint], except it tries to fit
 * itself inline if possible. If not possible, it falls back to the next line.
 */
class CramSiblingConstraint : SiblingConstraint() {
    override var cachedValue = 0f
    override var recalculate = true
    override var constrainTo: UIComponent? = null

    override fun getXPositionImpl(component: UIComponent): Float {
        val index = component.parent.children.indexOf(component)

        if (index == 0) {
            return component.parent.getLeft()
        }

        val sibling = component.parent.children[index - 1]

        if (sibling.getRight() + component.getWidth() < component.parent.getRight()) {
            return sibling.getRight()
        }

        return component.parent.getLeft()
    }

    override fun getYPositionImpl(component: UIComponent): Float {
        val index = component.parent.children.indexOf(component)

        if (index == 0) {
            return component.parent.getTop()
        }

        val sibling = component.parent.children[index - 1]

        if (sibling.getRight() + component.getWidth() < component.parent.getRight()) {
            return sibling.getTop()
        }

        return getLowestPoint(sibling, component.parent, index)
    }

    override fun to(component: UIComponent) {
        throw(IllegalStateException("Constraint.to(UIComponent) is not available in this context!"))
    }
}