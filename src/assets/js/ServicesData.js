export default {
  data() {
    return {
      services: [
        {
          id: '1',
          name: '遛狗',
          intro:
            '高效便捷的办法让您的狗狗活跃开心...<br>在服务了一整天之后，你是否已经疲惫不堪，回到家还需要陪伴狗狗遛弯和玩耍？在您休息时，让SL帮您遛狗，满足狗狗应有的散步需求。',
          img: 'https://puphub.oss-cn-fuzhou.aliyuncs.com/serviceContent/SL9%20copy.jpeg',
          icon: {
            focus: '/img/service-icon/dog_color.svg',
            blur: '/img/service-icon/dog_white.svg',
          },
          detail: [
            {
              id: 1,
              title: '遛狗价格',
              content: [
                '工作日7AM-9AM',
                '15分钟 50元人民币',
                '30分钟 70元人民币',
                '60分钟 90元人民币',
              ],
            },
            {
              id: 2,
              title: '多个狗狗',
              content: ['单只以外的每只狗狗 +20元人民币'],
            },
          ],
        },
        {
          id: '2',
          name: '上门查看',
          intro:
            '只需要30或60分钟<br>家里迎来新的宠物或没有时间照看？预定一个快速上门服务的宠物保姆，确保万事大吉。',
          img: 'https://puphub.oss-cn-fuzhou.aliyuncs.com/serviceContent/SL4-cat%20copy%202.jpeg',
          icon: {
            focus: '/img/service-icon/check_color.svg',
            blur: '/img/service-icon/check_white.svg',
          },
          detail: [
            {
              id: 1,
              title: '上门查看服务包括',
              content: [
                '意外清扫',
                '铲屎',
                '短时陪玩互动',
                '图片/视频动态发送',
                '添加食物/饮水',
                '按需喂药',
              ],
            },
            {
              id: 2,
              title: '上门查看服务价格',
              content: ['30分钟 80元人民币', '60分钟 100元人民币'],
            },
            {
              id: 3,
              title: '多个宠物',
              content: ['单只以外的每只狗狗 +20元人民币'],
            },
          ],
        },

        {
          id: '3',
          name: '狗狗寄养',
          intro:
            '动物呆在无笼式且舒适的宠物保姆家。<br>当您不在期间，SL喜欢宠物的保姆团队可以让您放心，没有人比我们更能让您的狗狗感到受欢迎，安全和舒适。您的动物将免于牢笼束缚，遛弯随行，并得到他们需要的和应有的关怀。',
          img: 'https://puphub.oss-cn-fuzhou.aliyuncs.com/serviceContent/DayCare.jpg',
          icon: {
            focus: '/img/service-icon/doghouse_color.svg',
            blur: '/img/service-icon/doghouse_white.svg',
          },
          detail: [
            {
              id: 1,
              title: '附加服务：',
              content: [
                '日常遛狗',
                '无限陪玩时间',
                '图片/视频动态发送',
                '按需购买额外的宠粮',
                '按需喂药',
                '灵活的交送和接回时间',
                '收信',
                '如果您要求针对您宠物的服务，请提出你的建议。',
              ],
            },
            {
              id: 2,
              title: '狗狗寄养服务价格',
              content: [
                '群体狗狗寄养 150元人民币(多个狗狗)',
                '专属寄养：200RMB（没有其他狗狗）',
              ],
            },
            {
              id: 3,
              title: '多个狗狗',
              content: ['单只以外的每只狗狗 +20元人民币'],
            },
          ],
        },
        {
          id: '4',
          name: '上门住家托管',
          intro:
            '通过宠物保姆上门服务，宠物得以休息、安适、放松。<br>我们的目标是让您容易接受这个转变过程，让宠物在自己舒适的家中保持日常生活习惯，消除宠物的紧张心里。别再担心，联系我们一个训练有素的宠物保姆。不管需要一天或一个星期时间，我们帮你安排。',
          img: 'https://puphub.oss-cn-fuzhou.aliyuncs.com/serviceContent/SL8%20copy.jpeg',
          icon: {
            focus: '/img/service-icon/cat_color.svg',
            blur: '/img/service-icon/cat_white.svg',
          },
          detail: [
            {
              id: 1,
              title: '附加服务：',
              content: [
                '日常遛狗',
                '无限陪玩时间',
                '清洗宠物餐具',
                '图片/视频动态发送',
                '按需购买额外的宠粮',
                '按需喂药',
                '灵活的交送和接回时间',
                '收信',
                '植物浇水',
                '家庭安全检查 - 更换灯泡、窗帘和/或百叶窗',
                '如果您要求针对您宠物的服务，请提出你的建议。',
              ],
            },
            {
              id: 2,
              title: '上门住家托管价格',
              content: ['狗狗：200元人民币'],
            },
            {
              id: 2,
              title: '多个宠物',
              content: ['单只以外的狗狗 20元人民币/每狗/每天'],
            },
          ],
        },
        {
          id: '5',
          name: '陪诊',
          intro:
            '无论凌晨、白天还是晚上，我们提供帮助确保您的宠物健康快乐。<br>尽管我们竭尽全力提前周密地计划，偶尔还是会出现宠物生病就诊的情况。不论在工作日还是周末，与其重新规划日程，不如联系我们，让我们帮您在您附近找到可以提供帮助的宠物保姆。',
          img: 'https://puphub.oss-cn-fuzhou.aliyuncs.com/serviceContent/SL-vetrun.jpeg',
          icon: {
            focus: '/img/service-icon/doctor_color.svg',
            blur: '/img/service-icon/doctor_white.svg',
          },
          detail: [
            {
              id: 1,
              title: '附加服务：',
              content: [
                '送诊和接诊',
                '根据需要可直接联系兽医铲屎',
                '图片/视频动态发送',
                '按需喂药',
                '如果您要求针对您宠物的服务，请提出你的建议。',
              ],
            },
            {
              id: 2,
              title: '陪诊服务价格',
              content: [
                '陪诊200元人民币',
                '* *每次陪诊，不包含出租车费或者其他额外的费用。',
              ],
            },
            {
              id: 3,
              title: '多个宠物',
              content: ['单只以外的每只狗狗 +20元人民币'],
            },
          ],
        },
        {
          id: '6',
          name: '狗狗培训',
          intro:
            '学习永远不会太迟...<br>尽管我们竭尽全力提前周密地计划，偶尔还是会出现宠物生病就诊的情况。不论在工作日还是周末，与其重新规划日程，不如联系我们，让我们帮您在您附近找到可以提供帮助的宠物保姆。',
          img: 'https://puphub.oss-cn-fuzhou.aliyuncs.com/serviceContent/IMG_5123.jpg',
          icon: {
            focus: '/img/service-icon/training_color.svg',
            blur: '/img/service-icon/training_white.svg',
          },
          detail: [
            {
              id: 1,
              title: '幼犬培训',
              content: ['长牙/啃咬', '初始牵引', '大小便训练'],
            },
            {
              id: 2,
              title: '遛狗训练',
              content: ['牵引拉拽', '牵引躁动', '撕咬牵引带'],
            },
            {
              id: 3,
              title: '基本的命令',
              content: [
                '蹲下',
                '别动',
                '躺下',
                '*狗狗训练要求不同，训练价格不一样。联系我们匹配一位你附近的训狗师。',
              ],
            },
          ],
        },
      ],
    };
  },
};
