/*
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in thCut even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.catrainbow.nocheatplus.checks

import cn.nukkit.event.Event
import net.catrainbow.nocheatplus.NoCheatPlus
import net.catrainbow.nocheatplus.checks.moving.MovingCheckListener
import net.catrainbow.nocheatplus.feature.ITickListener
import net.catrainbow.nocheatplus.feature.wrapper.WrapperPacketEvent

/**
 * Check Tick Listener
 *
 * @author Catrainbow
 */
open class CheckListener(protected val type: CheckType) : ITickListener {

    private val subListeners: ArrayList<CheckListener> = ArrayList()
    override fun onTick(event: Event) {
        if (event is WrapperPacketEvent) for (check in NoCheatPlus.instance.getAllNCPCheck().values) check.onCheck(event)
    }

    fun prepareDefault(): CheckListener {
        this.onEnabled()
        return this
    }

    override fun onEnabled() {
        this.subListeners.add(MovingCheckListener())
    }

    override fun onDisabled() {
    }

    protected fun addCheck(check: Check) {
        NoCheatPlus.instance.getComManager().registerCom(check)
    }

}