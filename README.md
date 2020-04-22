# AntiBreakBlock
此插件用于阻止玩家在指定世界破坏特定的方块。

**可设置阻止类型为：**
1. 仅允许破坏指定方块
2. 不允许破坏指定方块

插件可查询手持物品的材质ID（Material Type）

**插件指令:**
1. /abb reload - 重载插件配置文件
2. /abb info   - 查询手持物品的ID

**插件配置:**
1. enable 开关用于开启关闭插件功能
2. break 开关为是否允许破坏，设置为true将可以破坏方块，反之会被阻止
3. cancelDropITEM 开关为是否取消物品掉落，设置为true将不会掉落物品
4. cancelDropEXP 开关为是否掉落经验值，设置为 true 将不会产生经验球
5. sendMessage 开关为是否向玩家发送阻止信息
6. Message 为发送给玩家的信息，此项可使用颜色符号
7. version 项用于检测是否有新版本, 请勿随意更改

插件未对 Mod 服务器进行适配，尚不清楚是否适用于 Mod 端

如果使用途中遇到了Bug，或者对插件改进有建议，请提交 Issues
