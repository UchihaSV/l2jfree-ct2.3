# Made by disKret
import sys
from com.l2jfree import Config
from com.l2jfree.gameserver.model.quest import State
from com.l2jfree.gameserver.model.quest import QuestState
from com.l2jfree.gameserver.model.quest.jython import QuestJython as JQuest

qn = "36_MakeASewingKit"

#ITEM
REINFORCED_STEEL = 7163
ARTISANS_FRAME   = 1891
ORIHARUKON       = 1893
SEWING_KIT       = 7078

#NEEDED
FERRIS_STEEL            = 5
FERRIS_FRAME            = 10
FERRIS_ORIHARUKON       = 10
FERRIS_REINFORCED_STEEL = 10

#NPC
FERRIS = 30847
ENCHANTED_IRON_GOLEM = 20566

class Quest (JQuest) :

 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [REINFORCED_STEEL]

 def onEvent (self,event,st) :
   htmltext = event
   cond = st.getInt("cond")
   if event == "30847-1.htm" and cond == 0 :
     st.set("cond","1")
     st.setState(State.STARTED)
     st.playSound("ItemSound.quest_accept")
   if event == "30847-3.htm" and cond == 2 :
     st.takeItems(REINFORCED_STEEL,FERRIS_STEEL)
     st.set("cond","3")
   return htmltext

 def onTalk (self,npc,player) :
   htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
   st = player.getQuestState(qn)
   if not st : return htmltext
   id = st.getState()
   cond = st.getInt("cond")
   if cond == 0 and st.getQuestItemsCount(SEWING_KIT) == 0 :
     fwear=player.getQuestState("37_PleaseMakeMeFormalWear")
     if fwear:
         if fwear.get("cond") == "6" :
           htmltext = "30847-0.htm"
         else:
           st.exitQuest(1)
     else:
       st.exitQuest(1)
   elif st.getQuestItemsCount(REINFORCED_STEEL) >= FERRIS_STEEL :
     htmltext = "30847-2.htm"
   elif cond == 3 and st.getQuestItemsCount(ORIHARUKON) >= FERRIS_ORIHARUKON and st.getQuestItemsCount(ARTISANS_FRAME) >= FERRIS_FRAME :
     st.takeItems(ORIHARUKON,FERRIS_ORIHARUKON)
     st.takeItems(ARTISANS_FRAME,FERRIS_FRAME)
     st.giveItems(SEWING_KIT,int(1))
     st.playSound("ItemSound.quest_finish")
     htmltext = "30847-4.htm"
     st.exitQuest(1)
   return htmltext

 def onKill(self,npc,player,isPet):
   partyMember = self.getRandomPartyMember(player,"1")
   if not partyMember : return
   st = partyMember.getQuestState(qn)
   
   count = st.getQuestItemsCount(REINFORCED_STEEL)
   if count < FERRIS_REINFORCED_STEEL :
     st.giveItems(REINFORCED_STEEL,int(1))
     if count == (FERRIS_REINFORCED_STEEL - 1) :
       st.playSound("ItemSound.quest_middle")
       st.set("cond","2")
     else:
       st.playSound("ItemSound.quest_itemget")
   return

QUEST       = Quest(36,qn,"Make A Sewing Kit")

QUEST.addStartNpc(FERRIS)

QUEST.addTalkId(FERRIS)

QUEST.addKillId(ENCHANTED_IRON_GOLEM)
