package com.greencart.backend;

import com.greencart.backend.model.Plant;
import com.greencart.backend.repository.PlantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PlantRepository plantRepository) {
        return args -> {
            if (plantRepository.count() == 0) {
                String[][] plantData = {
                    {"Monstera Deliciosa", "A beautiful tropical plant known for its natural leaf holes. Perfect for adding a jungle vibe to any room.", "35.00", "/images/monstera.png", "Water every 1-2 weeks", "Bright, indirect sunlight", "true", "true"},
                    {"Snake Plant", "One of the easiest plants to care for. It filters indoor air and looks highly architectural.", "22.50", "/images/snake_plant.png", "Water every 2-3 weeks", "Low to bright indirect light", "true", "true"},
                    {"Fiddle Leaf Fig", "A stunning statement plant with large, violin-shaped leaves.", "45.00", "/images/fiddle_leaf.png", "Water every 1-2 weeks", "Bright, indirect sunlight", "false", "false"},
                    {"Peace Lily", "A beautiful, easy-care plant with dark green leaves and elegant white flowers.", "28.00", "/images/peace_lily.png", "Keep soil consistently moist", "Low to medium indirect light", "true", "false"},
                    
                    {"Aloe Vera", "A succulent plant species of the genus Aloe. It has medicinal uses and is very easy to grow.", "15.00", "/images/snake_plant.png", "Water every 3 weeks", "Bright, indirect sunlight", "true", "true"},
                    {"Spider Plant", "Considered one of the most adaptable of houseplants and the easiest to grow.", "18.00", "/images/peace_lily.png", "Water every week", "Bright, indirect sunlight", "true", "true"},
                    {"Pothos", "An easy-care trailing plant that looks great in hanging baskets or on shelves.", "16.50", "/images/monstera.png", "Water every 1-2 weeks", "Low to bright indirect light", "true", "true"},
                    {"ZZ Plant", "Characterized by its shiny, wide, oval-shaped leaves that shoot upward.", "25.00", "/images/snake_plant.png", "Water every 2-3 weeks", "Low to bright indirect light", "true", "true"},
                    {"Rubber Plant", "A popular houseplant with thick, glossy leaves that can grow quite large.", "32.00", "/images/fiddle_leaf.png", "Water every 1-2 weeks", "Bright, indirect sunlight", "true", "false"},
                    {"Cast Iron Plant", "Earns its name by surviving under conditions that would kill most other plants.", "24.00", "/images/peace_lily.png", "Water every 2 weeks", "Low to moderate light", "true", "true"},
                    
                    {"Philodendron", "A large genus of flowering plants known for their impressive foliage.", "20.00", "/images/monstera.png", "Water every week", "Bright, indirect sunlight", "true", "true"},
                    {"Chinese Evergreen", "A highly decorative plant with several interesting varieties.", "22.00", "/images/snake_plant.png", "Water every 1-2 weeks", "Low to moderate light", "true", "true"},
                    {"Bird's Nest Fern", "A unique fern with large, undivided fronds that resemble banana leaves.", "26.00", "/images/peace_lily.png", "Keep soil moist", "Medium indirect light", "false", "false"},
                    {"Calathea", "Known for their stunningly beautiful leaves with complex patterns.", "30.00", "/images/fiddle_leaf.png", "Keep soil moist", "Medium indirect light", "false", "false"},
                    {"Majesty Palm", "A robust, tropical palm with graceful, feathery fronds.", "40.00", "/images/monstera.png", "Water every week", "Bright, indirect sunlight", "false", "false"},
                    
                    {"Boston Fern", "A classic houseplant known for its lush, drooping fronds.", "22.00", "/images/peace_lily.png", "Keep soil moist", "Bright, indirect sunlight", "false", "false"},
                    {"String of Pearls", "A captivating succulent with pea-like leaves trailing down thin stems.", "18.00", "/images/snake_plant.png", "Water every 2-3 weeks", "Bright, indirect sunlight", "false", "true"},
                    {"Jade Plant", "A popular succulent houseplant with fleshy, oval-shaped leaves.", "20.00", "/images/fiddle_leaf.png", "Water every 2-3 weeks", "Bright, indirect sunlight", "true", "true"},
                    {"English Ivy", "A versatile evergreen vine that is easy to grow indoors.", "15.00", "/images/monstera.png", "Water every week", "Bright, indirect sunlight", "true", "true"},
                    {"Peperomia", "A diverse group of small, easy-to-care-for houseplants.", "16.00", "/images/peace_lily.png", "Water every 1-2 weeks", "Medium to bright indirect light", "true", "true"},
                    
                    {"Air Plant", "Unique plants that don't need soil to grow.", "12.00", "/images/snake_plant.png", "Mist weekly or soak every 2 weeks", "Bright, indirect sunlight", "true", "true"},
                    {"Bonsai Tree", "A miniature tree cultivated for ornamental purposes.", "50.00", "/images/fiddle_leaf.png", "Water when topsoil feels dry", "Bright, indirect sunlight", "false", "false"},
                    {"Orchid", "Elegant flowering plants known for their long-lasting blooms.", "35.00", "/images/peace_lily.png", "Water weekly", "Bright, indirect sunlight", "false", "false"},
                    {"Pilea Peperomioides", "Also known as the Chinese money plant, featuring round, coin-like leaves.", "24.00", "/images/monstera.png", "Water every week", "Bright, indirect sunlight", "true", "true"},
                    {"Croton", "A vibrant plant with wildly colorful, variegated leaves.", "28.00", "/images/fiddle_leaf.png", "Keep soil evenly moist", "Bright, indirect sunlight", "false", "false"}
                };

                for (String[] data : plantData) {
                    Plant p = new Plant();
                    p.setName(data[0]);
                    p.setDescription(data[1]);
                    p.setPrice(Double.parseDouble(data[2]));
                    p.setImageUrl(data[3]);
                    p.setWaterNeeds(data[4]);
                    p.setSunlight(data[5]);
                    p.setIsBeginnerFriendly(Boolean.parseBoolean(data[6]));
                    p.setIsLowMaintenance(Boolean.parseBoolean(data[7]));
                    plantRepository.save(p);
                }
            }
        };
    }
}
