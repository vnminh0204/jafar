import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 2) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 11) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 61) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 31) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 27) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 61) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 51) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 47) 
    , Push regC 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

