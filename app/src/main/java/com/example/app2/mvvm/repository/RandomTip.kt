package com.example.app2.mvvm.repository

class RandomTip {
    private val nutritionTips: MutableList<String> = mutableListOf(
        "Beba pelo menos 2 litros de água por dia para manter seu corpo hidratado e otimizar seu desempenho nos treinos.",
        "Inclua fontes de proteína magra, como peito de frango, peixe e ovos, para ajudar na recuperação muscular.",
        "Consuma carboidratos complexos, como batata-doce e arroz integral, para fornecer energia duradoura durante os treinos.",
        "Evite alimentos processados e ricos em açúcares, pois eles podem prejudicar seu desempenho e saúde.",
        "Inclua gorduras saudáveis na sua alimentação, como abacate, azeite de oliva e nozes."
    )

    private val workoutTips: MutableList<String> = mutableListOf(
        "Faça exercícios de força, como levantamento de peso, para aumentar sua massa muscular e melhorar o metabolismo.",
        "Realize atividades aeróbicas, como caminhada rápida ou corrida, para melhorar sua resistência e saúde cardiovascular.",
        "Varie os exercícios para evitar a monotonia e garantir que todos os grupos musculares sejam trabalhados.",
        "Não se esqueça de aquecer antes de treinar e alongar após o treino para evitar lesões.",
        "Mantenha uma boa postura durante os exercícios para evitar dores e lesões."
    )

    fun randomNutritionTip(): String {
        return nutritionTips.random()
    }

    fun randomWorkoutTip(): String {
        return workoutTips.random()
    }
}