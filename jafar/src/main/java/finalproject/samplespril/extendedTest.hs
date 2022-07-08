import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 13) regF 
    , Jump (Abs 242) 
    , Nop 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 81) 
    , Nop 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue (-2)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 839) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 46) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 42) 
    , Push regC 
    , Pop regB 
    , Pop regA 
    , Compute Mul regA regB regA 
    , Push regA 
    , Load (ImmValue (-2)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regB regA regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Mul regA regB regA 
    , Push regA 
    , Load (ImmValue (-2)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute And regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 20) 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regB regA regA 
    , Push regA 
    , Load (ImmValue (-4)) regC 
    , Compute Add regF regC regC 
    , Pop regA 
    , Store regA (IndAddr regC) 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regB 
    , Jump (Ind regB) 
    , Nop 
    , Jump (Abs 191) 
    , Nop 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Gt regA regB regA 
    , Push regA 
    , Pop regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 171) 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 190) 
    , Nop 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Nop 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute NEq regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 136) 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue (-3)) regC 
    , Compute Add regF regC regC 
    , Pop regA 
    , Store regA (IndAddr regC) 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regB 
    , Jump (Ind regB) 
    , Nop 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regB 
    , Jump (Ind regB) 
    , Nop 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regE 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 1) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 6) regA 
    , Push regA 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regE 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 1) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regE 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 1) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 6) regA 
    , Push regA 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (ImmValue 13) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 677) 
    , Nop 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (DirAddr 13) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 5) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 465) regA 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-5)) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (DirAddr 13) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (DirAddr 13) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 553) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 134) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regA regB regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute And regA regB regA 
    , Push regA 
    , Pop regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 663) 
    , Load (DirAddr 10) regA 
    , Push regA 
    , Load (ImmValue 2) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (DirAddr 13) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (DirAddr 13) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Pop regB 
    , Pop regA 
    , Compute Mul regA regB regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 660) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 220) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regF 
    , Nop 
    , Load (ImmValue 13) regA 
    , Push regA 
    , Load (DirAddr 13) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (DirAddr 13) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 420) 
    , Load (DirAddr 10) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regE 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 1) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 132) regA 
    , Push regA 
    , Load (ImmValue 12) regA 
    , Push regA 
    , Load (ImmValue 321) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 839) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 839) 
    , Pop regD 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regB 
    , Pop regA 
    , Load (ImmValue 1) regC 
    , Load (IndAddr regA) regD 
    , Load (IndAddr regB) regE 
    , Compute Equal regD regE regD 
    , Compute And regC regD regC 
    , Load (ImmValue 1) regD 
    , Compute Add regA regD regA 
    , Compute Add regB regD regB 
    , Load (IndAddr regA) regD 
    , Load (IndAddr regB) regE 
    , Compute Equal regD regE regD 
    , Compute And regC regD regC 
    , Load (ImmValue 1) regD 
    , Compute Add regA regD regA 
    , Compute Add regB regD regB 
    , Load (IndAddr regA) regD 
    , Load (IndAddr regB) regE 
    , Compute Equal regD regE regD 
    , Compute And regC regD regC 
    , Load (ImmValue 1) regD 
    , Compute Add regA regD regA 
    , Compute Add regB regD regB 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

